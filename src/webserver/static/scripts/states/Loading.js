class Loading extends Phaser.State {

  constructor() {
    super();

    this.startGame = this.startGame.bind(this);
  }

  init(map) { this.map = map }

  create() {

    this.game.load.onLoadStart.add(() => {});
    this.game.load.onFileComplete.add(() => {});
    this.game.load.onLoadComplete.add(this.startGame);

    this.game.load.image('player', './assets/playerShip1_blue.png');
    this.game.load.image('enemy', './assets/playerShip1_red.png');
    this.game.load.image('player_bullet', './assets/lasers/laserBlue03.png');
    this.game.load.image('enemy_bullet', './assets/lasers/laserRed03.png');

    this.game.load.audio('laser1', './assets/bonus/sfx_laser1.ogg');
    this.game.load.audio('laser2', './assets/bonus/sfx_laser2.ogg');
    this.game.load.audio('zap', './assets/bonus/sfx_zap.ogg');
    this.game.load.audio('lose', './assets/bonus/sfx_lose.ogg');

    this.game.load.start();

  }

  startGame() {
    this.game.state.start("Playing", true, false, this.map);
  }

}