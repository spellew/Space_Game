class Ship {

  constructor({ meta, sprite, weapon, game }) {
    this.sprite = sprite;
    this.sprite.anchor.setTo(0.5, 0.5);
    this.sprite.scale.set(meta.scale, meta.scale);
    this.sprite.x = meta.width * meta.bounds * Math.random();
    this.sprite.y = meta.height * meta.bounds * Math.random();
    this.sprite.health = 100;
    this.sprite.maxHealth = 100;
    this.sprite.healthBar = new HealthBar(game, { x: this.sprite.x, y: this.sprite.y + 60, width: 85, height: 8, bg: { color: '#fff' }, bar: { color: '#2ecc71' } });
    this.sprite.speed = 625;
    this.sprite.weapon = weapon.weapon;
    this.sprite.weapon.bulletKillType = Phaser.Weapon.KILL_LIFESPAN;
    this.sprite.weapon.bulletLifespan = 750;
    this.sprite.weapon.bulletAngleOffset = 90;
    this.sprite.weapon.bulletSpeed = 1000;
    this.sprite.weapon.bulletWorldWrap = true;
    this.sprite.weapon.trackSprite(this.sprite, 0, 0);
    this.sprite.weapon.onFire.add(() => weapon.soundfx.play());
    game.physics.enable(this.sprite, Phaser.Physics.ARCADE);
  }
  
}