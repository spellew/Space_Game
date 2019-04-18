class Playing extends Phaser.State {

  init(map) { this.map = map }

  create() {

    // window.setInterval(() => console.log("isPlaying [map]: " + this.map.backgroundFx.isPlaying), 1000);
    // window.setInterval(() => console.log("isPlaying [game]: " + this.game.backgroundFx.isPlaying), 1000);

    // window.setTimeout(() => this.map.backgroundFx.pause(), 3000);
    // window.setTimeout(() => this.game.backgroundFx.resume(), 6000);

    this.paused = false;

    this.game.physics.startSystem(Phaser.Physics.ARCADE);

    this.game.add.tileSprite(0, 0, this.map.meta.width * this.map.meta.bounds, this.map.meta.height * this.map.meta.bounds, 'backdrop');
    this.game.world.setBounds(0, 0, this.map.meta.width * this.map.meta.bounds, this.map.meta.height * this.map.meta.bounds);

    this.map.enemies = [];
    this.map.enemies.group = this.game.add.group();
    this.map.player = new Player({ game: this.game, map: this.map });
    this.map.enemy = new Enemy({ game: this.game, map: this.map });

    this.zap = this.game.add.audio('zap', 4);

    this.text = this.game.add.text(this.map.meta.width * 0.5, this.map.meta.height - 75, "Player 1: WASD && ALT; Player 2: IJKL && CTRL;", {
      font: "32px Arial",
      fill: "#fff",
    });

    this.text.anchor.setTo(0.5, 0.5);
    
  }

  update () {

    this.map.player.sprite.body.velocity.x = 0;
    this.map.player.sprite.body.velocity.y = 0;
    this.map.player.sprite.body.angularVelocity = 0;

    this.map.enemy.sprite.body.velocity.x = 0;
    this.map.enemy.sprite.body.velocity.y = 0;
    this.map.enemy.sprite.body.angularVelocity = 0;

    if (this.map.player.sprite.alive && !this.paused) {

      if (this.game.input.keyboard.isDown(Phaser.Keyboard.A)) {
        this.map.player.sprite.body.angularVelocity = -400;
      } else if (this.game.input.keyboard.isDown(Phaser.Keyboard.D)) {
        this.map.player.sprite.body.angularVelocity = 400;
      }

      if (this.game.input.keyboard.isDown(Phaser.Keyboard.W)) {
        this.game.physics.arcade.velocityFromAngle(this.map.player.sprite.angle - 90, this.map.player.sprite.speed, this.map.player.sprite.body.velocity);
      } else if (this.game.input.keyboard.isDown(Phaser.Keyboard.S)) {
        this.game.physics.arcade.velocityFromAngle(this.map.player.sprite.angle - 90, -this.map.player.sprite.speed, this.map.player.sprite.body.velocity);
      }

      if (this.game.input.keyboard.isDown(Phaser.Keyboard.ALT)) {
        this.map.player.sprite.weapon.fire();
      }

    }

    if (this.map.enemy.sprite.alive && !this.paused) {

      if (this.game.input.keyboard.isDown(Phaser.Keyboard.J)) {
        this.map.enemy.sprite.body.angularVelocity = -400;
      } else if (this.game.input.keyboard.isDown(Phaser.Keyboard.L)) {
        this.map.enemy.sprite.body.angularVelocity = 400;
      }

      if (this.game.input.keyboard.isDown(Phaser.Keyboard.I)) {
        this.game.physics.arcade.velocityFromAngle(this.map.enemy.sprite.angle - 90, this.map.enemy.sprite.speed, this.map.enemy.sprite.body.velocity);
      } else if (this.game.input.keyboard.isDown(Phaser.Keyboard.K)) {
        this.game.physics.arcade.velocityFromAngle(this.map.enemy.sprite.angle - 90, -this.map.enemy.sprite.speed, this.map.enemy.sprite.body.velocity);
      }

      if (this.game.input.keyboard.isDown(Phaser.Keyboard.CONTROL)) {
        this.map.enemy.sprite.weapon.fire();
      }

    }

    if (!this.map.debug) {
      this.game.world.wrap(this.map.player.sprite, 0, true);
    }

    this.map.player.sprite.healthBar.setPosition(this.map.player.sprite.x, this.map.player.sprite.y + 60);
    this.map.player.sprite.weapon.fireAngle = this.map.player.sprite.angle - 90;

    this.game.physics.arcade.overlap(this.map.player.sprite.weapon.bullets, this.map.enemies.group, (bullet, enemy) => {
      bullet.kill();
      enemy.health -= 10;
      enemy.healthBar.setPercent(enemy.health / enemy.maxHealth * 100);
      if (enemy.health <= 0) {
        enemy.weapon.bullets.killAll();
        enemy.healthBar.kill();
        enemy.kill();
        this.paused = true;
        this.zap.play();
        window.setTimeout(() => this.game.state.start("GameOver", true, false, this.map), 750);
      }
    });

    this.map.enemies.group.forEach(enemy => {
      this.game.world.wrap(enemy, 0, true);
      enemy.healthBar.setPosition(enemy.x, enemy.y + 60);
      enemy.weapon.fireAngle = enemy.angle - 90;
      this.game.physics.arcade.overlap(enemy.weapon.bullets, this.map.player.sprite, (player, bullet) => {
        bullet.kill();
        player.health -= 10;
        player.healthBar.setPercent(player.health / player.maxHealth * 100);
        if (player.health <= 0) {
          player.weapon.bullets.killAll();
          player.healthBar.kill();
          player.kill();
          this.paused = true;
          this.zap.play();
          window.setTimeout(() => this.game.state.start("GameOver", true, false, this.map), 750);
        }
      });
    });

    this.game.physics.arcade.overlap(this.map.player.sprite, this.map.enemies.group, () => "overlap");

  }

}