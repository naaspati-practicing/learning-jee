let price = null, volume;

function update(json) {
    if (!price) {
        price = document.getElementById("price");
        volume = document.getElementById("volume");
    }
    if (json) {
        price.innerHTML = json.price;
        volume.innerHTML = json.volume;
    }
    
}
function startWebSocket() {
    const socket = new WebSocket('ws://localhost:8080/page/dukeetf');
    socket.onmessage = msg => update(JSON.parse(msg.data));
}
