
import dotenv from "dotenv";
dotenv.config();
import cors from "cors"
import OpenAI from "openai";
import express from "express";
import bodyParser from "body-parser";
import helmet from "helmet";

const app = express();

var corsOptions = {
    origin: 'http://localhost:8080/',
    optionsSuccessStatus: 200
}

app.use(helmet());
app.use(bodyParser.json());

app.use(express.static('public'));

const openai = new OpenAI({
    apiKey: process.env.OPENAI_API_KEY,
});

let assistant_id = "asst_...";

app.get('/', cors(corsOptions), function(req, res){
    res.sendFile('/public/index.html', { root: '.' });
});

app.post('/api/chat', async (req, res) => {
    const userMessage = req.body.message;

    try {
        const chatCompletion = await openai.chat.completions.create({
            messages: [{ role: "user", content: userMessage }],
            model: "gpt-4o-mini",
        });

        const reply = chatCompletion.choices[0].message.content;
        res.json({ reply });
    } catch (error) {
        console.error("Error with OpenAI API:", error);
        res.status(500).json({ reply: "Sorry, something went wrong!" });
    }
});

app.post("/assistant", async (req, res) => {
    try {
        // Create a Thread
        const threadResponse = await openai.beta.threads.create();
        const threadId = threadResponse.id;

        const userMessage = req.body.message;
  
        // Add a Message to a Thread
        await openai.beta.threads.messages.create(threadId, {
            role: "user",
            content: userMessage,
        });
  
        // Run the Assistant
        const runResponse = await openai.beta.threads.runs.create(threadId, {
            assistant_id: assistant_id,
        });

        // Check the Run status
        let run = await openai.beta.threads.runs.retrieve(threadId, runResponse.id);
        while (run.status !== "completed") {
            await new Promise((resolve) => setTimeout(resolve, 1000));
            run = await openai.beta.threads.runs.retrieve(threadId, runResponse.id);
        }
  
        // Display the Assistant's Response
        const messagesResponse = await openai.beta.threads.messages.list(threadId);
        const assistantResponses = messagesResponse.data.filter(msg => msg.role === 'assistant');
        const response = assistantResponses[0].content[0].text.value;
        res.json({ response });
  
    } catch (error) {
      console.error("Error with OpenAI API:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  });

app.listen(8080, () => {
    console.log("Server is running on http://localhost:8080");
});