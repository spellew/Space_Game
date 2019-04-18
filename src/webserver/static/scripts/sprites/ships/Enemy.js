class Enemy extends Ship {

  constructor({ game, map }) {
    super({
      game,
      meta: map.meta,
      sprite: game.add.sprite(0, 0, 'enemy'),
      weapon: {
        weapon: game.add.weapon(30, 'enemy_bullet'),
        soundfx: game.add.audio('laser2'),
      }
    });
    map.enemies.group.add(this.sprite);
  }

}