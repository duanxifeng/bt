package bt.torrent;

import org.junit.Test;

import java.util.function.Predicate;

import static bt.torrent.RarestFirstSelectionStrategy.regular;
import static org.junit.Assert.assertArrayEquals;

public class RarestFirstSelectionStrategyTest {

    private static final Predicate<Integer> acceptAllValidator = i -> true;

    @Test
    public void testSelection() {
        UpdatablePieceStatistics statistics = new UpdatablePieceStatistics(8);

        statistics.setPiecesCount(0, 0, 0, 0, 0, 0, 0, 0);
        assertArrayEquals(new Integer[0], regular().getNextPieces(statistics, 2, acceptAllValidator));

        statistics.setPiecesCount(0, 3, 0, 2, 1, 0, 0, 0);
        assertArrayEquals(new Integer[] {4, 3}, regular().getNextPieces(statistics, 2, acceptAllValidator));

        statistics.setPiecesCount(0, 1);
        assertArrayEquals(new Integer[] {0, 4}, regular().getNextPieces(statistics, 2, acceptAllValidator));
    }
}