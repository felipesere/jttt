package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Player;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerFactoryTest {

  @Test
  public void canRequestHumanAndComputer() {
    Player[] players = PlayerFactory.getPlayers(1);
    assertThat(players, are(HumanPlayer.class, AiPlayer.class));
  }

  @Test
  public void canRequestComputerAndHuman() {
    Player[] players = PlayerFactory.getPlayers(2);
    assertThat(players, are(AiPlayer.class,HumanPlayer.class));
  }

  @Test
  public void canRequestTwoAiPlayers() {
    Player[] players = PlayerFactory.getPlayers(3);
    assertThat(players, are(AiPlayer.class, AiPlayer.class));
  }

  @Test
  public void canRequestTwoHumanPlayers() {
    Player[] players = PlayerFactory.getPlayers(4);
    assertThat(players, are(HumanPlayer.class, HumanPlayer.class));
  }

  private Matcher<Player[]> are(Class ... klass) {
    return new TypeSafeMatcher<Player[]>() {
      @Override
      protected boolean matchesSafely(Player[] item) {
        if (item.length != klass.length) {
          return false;
        }
        for (int i = 0; i < item.length; i++) {
          if (!klass[i].isInstance(item[i])) {
            return false;
          }
        }
        return true;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(classes(klass));
      }

      private String classes(Class[] klass) {
        return Stream.of(klass).map(Class::getCanonicalName).collect(toList()).toString();
      }
    };
  }
}
