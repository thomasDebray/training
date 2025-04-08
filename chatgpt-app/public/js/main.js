document.getElementById('user-input').focus();

document.getElementById('chatForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const userInput = document.getElementById('user-input').value;
    if (!userInput) return;
    if (userInput.length>400) return;

    addMessageToChatBox('user', userInput);
    document.getElementById('user-input').value = '';
    const userInputId = document.getElementById('user-input');
    userInputId.disabled = true;
    userInputId.placeholder = "Veuillez patienter";

    const response = await fetch('/assistant', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ message: userInput }),
    });

    const data = await response.json();
    addMessageToChatBox('bot', data.response);
    userInputId.disabled = false;
    userInputId.placeholder = "Posez votre question sur Thomas";
    userInputId.focus();
});

function addMessageToChatBox(role, message) {
    const chatBox = document.getElementById('chat');
    const messageElement = document.createElement('div');
    messageElement.classList.add(role);
    if (role === 'user') {
        messageElement.innerHTML = `<strong>${message}</strong>`;
    } else {
        messageElement.innerHTML = message;
    }
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

function checkEmptyDiv() {
    const div = document.getElementById("chat");
    const arrow = document.getElementById("arrow");
    
    if (div.children.length === 1) {
        arrow.style.display = "block";
    } else {
        arrow.style.display = "none";
    }
}

// Vérification au chargement
checkEmptyDiv();

// Observer les changements dans le div
const observer = new MutationObserver(checkEmptyDiv);
observer.observe(document.getElementById("chat"), { childList: true });