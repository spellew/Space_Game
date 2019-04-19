window.addEventListener("load", () => {

  const connect = () => {

    const host = window.location.host || "localhost:8080";
    const ws = new WebSocket(`ws://${host}/io`);

    ws.onopen = function () {
      console.log("socket opened");
      ws.send(JSON.stringify({
        action: "register",
        nickname: "Foo"
      }));
    };

    ws.onmessage = function (evt) {
      console.log("socket received message");
      console.log(evt);
      const data = JSON.parse(evt.data);
      console.log(data);
      switch(data.action) {
        case "new-game":
          console.log("start new-game");
      }
    };

    ws.onerror = function (evt) {
      console.log("socket received error");
      console.log(evt);
      ws.close();
    };

    ws.onclose = function () {
      console.log("socket closed");
      window.setTimeout(connect, 500);
    };

  }

  connect();

  const height = window.innerHeight * window.devicePixelRatio;
  const width = height * (16 / 9);
  const scale = height / 1775;
  const bounds = 1;

  const map = {
    player: null,
    enemy: null,
    enemies: null,
    meta: { width: width, height: height, bounds: bounds, scale: scale },
    debug: false
  }
  
  class Game extends Phaser.Game {

    constructor() {

      super({
        width,
        height,
        renderer: Phaser.AUTO,
        transparent: true,
      });
    
      this.state.add("Boot", Boot);
      this.state.add("Menu", Menu);
      this.state.add("Loading", Loading);
      this.state.add("Playing", Playing);
      this.state.add("GameOver", GameOver);
      this.state.start("Boot", true, false, map);
      
    }
  
  }

  window.game = new Game();
  
});