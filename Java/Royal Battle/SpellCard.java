public class SpellCard extends Card {

  private Spell spell;
  
  public SpellCard(Spell spell) {
    super(spell.getCost(), spell.getImage(), spell.getSide(), (int)spell.getSize());
    this.spell = spell;
  }

  public void play() {
    spell.setPos(spell.getSide() == -1 ? 300 : 600, 20);
    System.out.println("spell played");
  }
}