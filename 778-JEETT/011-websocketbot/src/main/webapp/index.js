let wsocket,   // Websocket connection
    userName,  // User's name
    textarea,  // Chat area 
    wsconsole, // Websocket console area
    userlist;  // User list area

const byid = s => document.getElementById(s);

function init() {
    textarea = byid('textarea');
    wsconsole = byid('wsconsole');
    userlist = byid('userlist');
    wsocket = new WebSocket('ws://localhost:8080/page/websocketbot');
    wsocket.onmessage = onMessage;
    byid('name').focus();
    byid('consolediv').style.visibility = 'hidden';
}

window.addEventListener("load", init, false);

function add_wsconsole(text) {
    wsconsole.value += text;
    wsconsole.scrollTop = 999999;
}

function onMessage(evt) {
    const json = JSON.parse(evt.data);
    switch (json.type) {
        case 'chat':
            textarea.value += `${json.name}: ${json.target ? '@' + json.target + ' ' : ''} ${json.message}\n`;
            break;
        case 'info':
            textarea.value += `[-- ${json.info} --]\n`;
            break;
        case 'users':
            if (json.userlist)
                userlist.value = `Users:\n - ${json.userlist.join("\n - ")}\n`;
            else
                userlist.value = '';
            break;
        default:
            break;
    }

    textarea.scrollTop = 999999;
    /* Update the Websocket console area */
    add_wsconsole(`-> ${evt.data}\n`);
}

function sendJoin() {
    const input = byid('input'),
        name = byid('name'),
        join = byid('join');

    if (name.value) {
        const joinJson = { type: 'join', name: name.value };
        const jsonStr = JSON.stringify(joinJson);
        wsocket.send(jsonStr);

        name.disabled = true;
        join.disabled = true;
        input.disabled = false;

        userName = name.value;
        add_wsconsole(`<- ${jsonStr} \n`);
    }
}

// extract target, message from input.value
function parseInput() {
    let target = "";
    let message = "";
    
    byid('input').value
        .split(" ")
        .forEach(s => {
            if (s[0] === '@')
                target = s.substr(1);
            else
                message += s + " ";
        });

    target = target.replace(/\r?\n/gm, "");
    message = message.substring(0, message.length - 1);
    message = message.replace(/\r?\n/gm, "");

    return { target, message };
}

/* Send a chat message to the server (press ENTER on the input area) */
function sendMessage(e) {
    if (e.keyCode === 13 && input.value) {
        /* Create a chat message as a JavaScript object */
        const msgstr = parseInput();
        const chatMsg = {
            type: 'chat',
            name: userName,
            target: msgstr.target,
            message: msgstr.message
        }

        const jsonstr = JSON.stringify(chatMsg);
        wsocket.send(jsonstr);
        byid('input').value = '';
        add_wsconsole(`<- ${jsonstr}\n`);
    }
}

function checkJoin(e) {
    const name = byid('name');

    if (e.keyCode === 13 && name.value) {
        sendJoin();
        byid('input').focus();
    }
}

function showHideConsole() {
    const cbox = byid('showhideconsole');
    byid('consolediv').style.visibility = cbox.checked ? 'visible' : 'hidden';
}
