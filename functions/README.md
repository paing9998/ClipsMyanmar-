# ClipsMyanmar Cloud Functions

This directory contains the backend Cloud Functions for the ClipsMyanmar app, primarily for interacting with the Gemini API.

## Setup

1.  **Install Dependencies**: Navigate to this directory (`/functions`) in your terminal and run:
    ```bash
    npm install
    ```

2.  **Set Up Local Environment Variables**:
    -   Create a file named `.env` in this directory.
    -   Copy the contents of `.env.example` into it.
    -   Get an API key from [Google AI Studio](https://makersuite.google.com/app/apikey) and paste it into your `.env` file. This is for testing with the local Firebase Emulator.

## Deployment

1.  **Set Production Environment Variables**: Before deploying, you must set the Gemini API key in the production environment configuration. **Do not commit your API key to your repository.**
    ```bash
    firebase functions:config:set gemini.key="YOUR_API_KEY"
    ```
    *Replace `YOUR_API_KEY` with your actual key.*

2.  **Deploy the Function**: From the **root** directory of your Firebase project, run the deployment command:
    ```bash
    firebase deploy --only functions
    ```
    This will compile the TypeScript to JavaScript and upload the function to your Firebase project.
    