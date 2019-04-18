class Menu extends Phaser.State {

  constructor() {
    super();
    
    this.loadGame = this.loadGame.bind(this);
  }

  init(map) { this.map = map }

  create() {

    // console.log("menu");

    // console.log("this.game", this.game);

    this.game.add.tileSprite(0, 0, this.map.meta.width * this.map.meta.bounds, this.map.meta.height * this.map.meta.bounds, 'backdrop');

    this.text = this.game.add.text(this.map.meta.width * 0.5, this.map.meta.height * 0.5, "SPACE RACE", {
      // font: "bold 92pt kenvector_future",
      font: "bold 92pt arial",
      fill: "#fff"
    });

    this.text2 = this.game.add.text(this.map.meta.width * 0.5, this.map.meta.height - 75, "Press ENTER or SPACEBAR to start;", {
      font: "32px Arial",
      fill: "#fff",
    });

    this.text.anchor.setTo(0.5, 0.5);
    // this.text.alpha = 0;

    this.text2.anchor.setTo(0.5, 0.5);

    this.text.inputEnabled = true;
    this.text.events.onInputDown.add(this.loadGame, this);

    // this.game.add.tween(this.text).to({ alpha: 1 }, 1500, Phaser.Easing.Linear.None, true);

  }

  update() {
    if (this.game.input.keyboard.isDown(Phaser.Keyboard.ENTER) || (this.game.input.keyboard.isDown(Phaser.Keyboard.SPACEBAR))) {
      this.loadGame();
    }
  }

  loadGame() {
    this.game.state.start("Loading", true, false, this.map);
  }

}