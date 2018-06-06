package agents;

import agents.MovingVehicle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import org.newdawn.slick.geom.Vector2f;

@SarlSpecification("0.7")
@SarlElementType(21)
@SuppressWarnings("all")
public class SkillBasicMoving extends Skill implements MovingVehicle {
  public void install() {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Installing the skill");
  }
  
  public void uninstall() {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Uninstalling the skill");
  }
  
  public Vector2f moveVehicle(final Vector2f from, final Vector2f to, final double speed) {
    Vector2f newPos = new Vector2f();
    if (((from.x < to.x) && (from.y < to.y))) {
      Vector2f _vector2f = new Vector2f((from.x - 1), (from.y - 1));
      newPos = _vector2f;
    } else {
      if (((from.x > to.x) && (from.y > to.y))) {
        Vector2f _vector2f_1 = new Vector2f((from.x + 1), (from.y + 1));
        newPos = _vector2f_1;
      } else {
        if (((from.x > to.x) && (from.y == to.y))) {
          Vector2f _vector2f_2 = new Vector2f((from.x + 1), from.y);
          newPos = _vector2f_2;
        } else {
          if (((from.x > to.x) && (from.y < to.y))) {
            Vector2f _vector2f_3 = new Vector2f((from.x + 1), (from.y - 1));
            newPos = _vector2f_3;
          } else {
            if (((from.x == to.x) && (from.y > to.y))) {
              Vector2f _vector2f_4 = new Vector2f(from.x, (from.y + 1));
              newPos = _vector2f_4;
            } else {
              if (((from.x == to.x) && (from.y < to.y))) {
                Vector2f _vector2f_5 = new Vector2f(from.x, (from.y - 1));
                newPos = _vector2f_5;
              } else {
                if (((from.x < to.x) && (from.y > to.y))) {
                  Vector2f _vector2f_6 = new Vector2f((from.x - 1), (from.y + 1));
                  newPos = _vector2f_6;
                } else {
                  if (((from.x < to.x) && (from.y == to.y))) {
                    Vector2f _vector2f_7 = new Vector2f((from.x - 1), from.y);
                    newPos = _vector2f_7;
                  }
                }
              }
            }
          }
        }
      }
    }
    return newPos;
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || $0$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING = $0$getSkill(Logging.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @SyntheticMember
  public SkillBasicMoving() {
    super();
  }
  
  @SyntheticMember
  public SkillBasicMoving(final Agent agent) {
    super(agent);
  }
}
