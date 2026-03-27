# textX: Intelligent Document Scanning & AI Analysis System

![textX Icon](https://deveshrx.github.io/Text-Master-OCR/pics/Play-Store-GraphicsSS-1.png)

## 📝 Project Overview
**textX** is an advanced Android application designed to bridge the gap between physical documents and digital intelligence. Unlike traditional OCR scanners that only extract text, this application utilizes State-of-the-Art Artificial Intelligence (**Llama 3.3 via Groq**) to summarize content and extract meaningful keywords automatically.

This project demonstrates proficiency in Android development, Cloud API integration, and local data persistence.

## ✨ Key Features
*   **High-Accuracy OCR**: Utilizes Google ML Kit to recognize text from images with up to 99% accuracy.
*   **AI Intelligent Summarization**: Automatically condenses long scanned documents into two concise sentences using the `llama-3.3-70b-versatile` model via Groq.
*   **Automated Tagging**: Extracts relevant keywords from the text to assist in document categorization.
*   **Local Persistence (History)**: Saves all scans, summaries, and keywords locally using the Room Database Library (SQLite abstraction).
*   **Real-time Processing**: Implements multi-threading (Executors) to ensure a smooth, non-blocking user interface during AI analysis.
*   **History Management**: A dedicated dashboard to view, delete, and re-summarize previous scans.

## 🛠 Tech Stack
*   **Language**: Java
*   **Minimum SDK**: Android 26 (Oreo)
*   **OCR Engine**: Google ML Kit (Vision API)
*   **AI Backend**: Groq Cloud API (Inference Engine)
*   **Model**: Meta Llama 3.3 (70B)
*   **Networking**: OkHttp 3 / JSON Parsing
*   **Database**: Android Room Persistence Library
*   **UI Components**: RecyclerView, CardView, Material Design 3, CameraX

## 🏗 System Architecture
The application follows a modular architecture:
1.  **UI Layer**: Activities handle user interaction (Camera capture, History list, Results).
2.  **Recognition Layer**: Google ML Kit processes the bitmap from the camera to extract raw strings.
3.  **Intelligence Layer (`AIManager.java`)**: The extracted text is sent via a secure REST API to the Groq Cloud, where the Llama model performs natural language processing.
4.  **Data Layer (RoomDB)**: Results are stored in a local SQLite database for offline access.

## 🚀 Installation & Setup
1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/deveshrx/Text-Master-OCR.git
    ```
2.  **Open in Android Studio**: Ensure you have Android Studio Ladybug or newer.
3.  **API Key Configuration**: Open `AIManager.java` and ensure your Groq Cloud API Key is present in the `groqApiKey` variable.
4.  **Build & Run**: Deploy to a physical Android device or an emulator with Camera support.

## 📸 Screenshots
![Screenshots](https://deveshrx.github.io/Text-Master-OCR/pics/Play-Store-GraphicsSS-6.png)
![Screenshots](https://deveshrx.github.io/Text-Master-OCR/pics/Play-Store-GraphicsSS-7.png)

## 🔮 Future Enhancements
*   **Search/Filters**: Intelligent keyword searching within the history.
*   **Cloud Sync**: Firebase Authentication and Firestore synchronization for cross-device access.
*   **Export Options**: One-tap export of summaries to PDF and .docx files.

## 📜 License
This project is developed for educational purposes and is available under the MIT License.
