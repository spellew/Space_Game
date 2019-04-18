class GameOver extends Phaser.State {

  constructor() {
    super();

    this.playGame = this.playGame.bind(this);
  }

  init(map) { this.map = map }

  create() {

    this.game.add.tileSprite(0, 0, this.map.meta.width * this.map.meta.bounds, this.map.meta.height * this.map.meta.bounds, 'backdrop');

    this.lose = this.game.add.audio('lose', 6);

    this.text = this.game.add.text(this.map.meta.width * 0.5, this.map.meta.height * 0.5, "GAME OVER", {
      // font: "bold 92pt kenvector_future",
      font: "bold 92pt arial",
      fill: "#fff"
    });

    this.text2 = this.game.add.text(this.map.meta.width * 0.5, this.map.meta.height - 75, "Press ENTER or SPACEBAR to restart; ESC to go back to menu;", {
      font: "32px Arial",
      fill: "#fff",
    });

    this.text.anchor.setTo(0.5, 0.5);
    this.text2.anchor.setTo(0.5, 0.5);

    this.text.inputEnabled = true;
    this.text.events.onInputDown.add(this.playGame, this);

    window.setTimeout(() => this.lose.play(), 187.5);

  }

  update() {
    if (this.game.input.keyboard.isDown(Phaser.Keyboard.ESC)) this.exitGame();
    if (this.game.input.keyboard.isDown(Phaser.Keyboard.ENTER) || (this.game.input.keyboard.isDown(Phaser.Keyboard.SPACEBAR))) this.playGame();
  }

  exitGame() {
    this.game.state.start("Menu", true, false, this.map);
  }

  playGame() {
    this.game.state.start("Playing", true, false, this.map);
  }

}