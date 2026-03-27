package devesh.app.ocr.api;

import java.util.List;

public class AIResponse {
    public List<Choice> choices;

    public static class Choice {
        public Message message;
    }

    public static class Message {
        public String role;
        public String content;
    }
}
