package agents;

import agents.MovingVehicle;
import framework.math.Point2f;
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
  
  public Point2f moveVehicle(final Point2f from, final Point2f to, final double speed) {
    Point2f newPos = new Point2f();
    if (((from.getX() < to.getX()) && (from.getY() < to.getY()))) {
      float _x = from.getX();
      float _minus = (_x - 1);
      float _y = from.getY();
      float _minus_1 = (_y - 1);
      Point2f _point2f = new Point2f(_minus, _minus_1);
      newPos = _point2f;
    } else {
      if (((from.getX() > to.getX()) && (from.getY() > to.getY()))) {
        float _x_1 = from.getX();
        float _plus = (_x_1 + 1);
        float _y_1 = from.getY();
        float _plus_1 = (_y_1 + 1);
        Point2f _point2f_1 = new Point2f(_plus, _plus_1);
        newPos = _point2f_1;
      } else {
        if (((from.getX() > to.getX()) && (from.getY() == to.getY()))) {
          float _x_2 = from.getX();
          float _plus_2 = (_x_2 + 1);
          float _y_2 = from.getY();
          Point2f _point2f_2 = new Point2f(_plus_2, _y_2);
          newPos = _point2f_2;
        } else {
          if (((from.getX() > to.getX()) && (from.getY() < to.getY()))) {
            float _x_3 = from.getX();
            float _plus_3 = (_x_3 + 1);
            float _y_3 = from.getY();
            float _minus_2 = (_y_3 - 1);
            Point2f _point2f_3 = new Point2f(_plus_3, _minus_2);
            newPos = _point2f_3;
          } else {
            if (((from.getX() == to.getX()) && (from.getY() > to.getY()))) {
              float _x_4 = from.getX();
              float _y_4 = from.getY();
              float _plus_4 = (_y_4 + 1);
              Point2f _point2f_4 = new Point2f(_x_4, _plus_4);
              newPos = _point2f_4;
            } else {
              if (((from.getX() == to.getX()) && (from.getY() < to.getY()))) {
                float _x_5 = from.getX();
                float _y_5 = from.getY();
                float _minus_3 = (_y_5 - 1);
                Point2f _point2f_5 = new Point2f(_x_5, _minus_3);
                newPos = _point2f_5;
              } else {
                if (((from.getX() < to.getX()) && (from.getY() > to.getY()))) {
                  float _x_6 = from.getX();
                  float _minus_4 = (_x_6 - 1);
                  float _y_6 = from.getY();
                  float _plus_5 = (_y_6 + 1);
                  Point2f _point2f_6 = new Point2f(_minus_4, _plus_5);
                  newPos = _point2f_6;
                } else {
                  if (((from.getX() < to.getX()) && (from.getY() == to.getY()))) {
                    float _x_7 = from.getX();
                    float _minus_5 = (_x_7 - 1);
                    float _y_7 = from.getY();
                    Point2f _point2f_7 = new Point2f(_minus_5, _y_7);
                    newPos = _point2f_7;
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
