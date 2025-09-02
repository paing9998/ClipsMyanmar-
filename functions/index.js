const functions = require("firebase-functions");
const admin = require("firebase-admin");
const { GoogleGenerativeAI } = require("@google/generative-ai");

admin.initializeApp();
const db = admin.firestore();

// IMPORTANT: Set your Gemini API Key in the Cloud Function environment
// Run this command in your terminal:
// firebase functions:config:set gemini.key="YOUR_API_KEY"
const genAI = new GoogleGenerativeAI(functions.config().gemini.key);

/**
 * Auth Trigger: Assigns the 'isAdmin' role to the very first user who signs up.
 */
exports.onFirstUserCreate = functions.auth.user().onCreate(async (user) => {
  const userRef = db.collection("users").doc(user.uid);

  // Check if any other users exist.
  const snapshot = await db.collection("users").limit(2).get();

  let isAdmin = false;
  if (snapshot.size === 1) {
    // This is the first document being created in the 'users' collection.
    isAdmin = true;
    console.log(`Assigning admin role to the first user: ${user.email}`);
  }

  return userRef.set({
    email: user.email,
    isAdmin: isAdmin,
    createdAt: admin.firestore.FieldValue.serverTimestamp(),
  });
});


/**
 * HTTPS Callable Function: Generates movie details using Gemini.
 * Accepts { title: string } and returns a rich movie object.
 */
exports.generateMovie = functions.https.onCall(async (data, context) => {
  // Authentication check
  if (!context.auth) {
    throw new functions.https.HttpsError(
      "unauthenticated",
      "The function must be called while authenticated.",
    );
  }

  const title = data.title;
  if (!title || typeof title !== "string") {
    throw new functions.https.HttpsError(
      "invalid-argument",
      "The function must be called with one argument 'title' that is a string.",
    );
  }

  try {
    const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });
    const prompt = `
      You are a movie database expert. For the movie titled "${title}", provide the following details in a strict JSON format.
      - rating: A number between 0 and 10, with one decimal place.
      - review_en: A concise, engaging 2-3 sentence review in English.
      - review_mm: Translate the English review accurately into Burmese (Myanmar).
      - release_year: The integer year the movie was released.
      - genres: An array of 2-4 relevant genres as strings.
      - poster_prompt: A vivid, descriptive prompt for an AI image generator to create a cinematic poster for this movie, focusing on mood, key characters, and setting.

      Example output for "Inception":
      {
        "rating": 8.8,
        "review_en": "A mind-bending thriller where thieves steal information by entering people's dreams. Visually stunning and intellectually challenging, it's a cinematic masterpiece.",
        "review_mm": "စိတ်ကိုလှုပ်ခတ်စေသော သည်းထိတ်ရင်ဖိုဇာတ်ကားဖြစ်ပြီး သူခိုးများသည် လူတို့၏အိပ်မက်ထဲသို့ဝင်၍ သတင်းအချက်အလက်များကို ခိုးယူကြသည်။ ရုပ်ပိုင်းဆိုင်ရာအရ အလွန်ကောင်းမွန်ပြီး ဉာဏ်ပညာပိုင်းအရ စိန်ခေါ်မှုရှိသော ဤဇာတ်ကားသည် ရုပ်ရှင်လောက၏ လက်ရာမြောက်တစ်ခုဖြစ်သည်။",
        "release_year": 2010,
        "genres": ["Sci-Fi", "Action", "Thriller"],
        "poster_prompt": "A cinematic movie poster of a lone man in a sharp suit standing in a hallway that is twisting and folding onto itself, defying gravity. The color palette is cool blues and grays, with a single warm light source. The mood is surreal and tense."
      }

      Now, generate the JSON for the movie: "${title}"
    `;

    const result = await model.generateContent(prompt);
    const response = await result.response;
    const text = response.text();

    // Clean the text to ensure it's valid JSON
    const jsonString = text.replace(/```json/g, "").replace(/```/g, "").trim();
    const movieData = JSON.parse(jsonString);

    return movieData;
  } catch (error) {
    console.error("Gemini API call failed:", error);
    throw new functions.https.HttpsError(
      "internal",
      "Failed to generate movie details.",
      error.message,
    );
  }
});
