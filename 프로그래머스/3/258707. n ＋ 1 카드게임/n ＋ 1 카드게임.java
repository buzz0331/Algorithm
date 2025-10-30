import java.util.*;

class Solution {
    private Set<Integer> myCards = new HashSet<>();
    private Set<Integer> tempCards = new HashSet<>();
    private int pair;
    private int round = 1;
    private int myCoin;
    private int n;

    public int solution(int coin, int[] cards) {
        this.myCoin = coin;
        this.n = cards.length;

        for (int i = 0; i < n / 3; i++) {
            myCards.add(cards[i]);
        }

        for (int card : myCards) { 
            if (myCards.contains(n + 1 - card)) {
                pair++;
            }
        }
        pair /= 2;

        for (int i = n / 3; i < n; i += 2) { 
            matchCard(cards[i]); 
            matchCard(cards[i + 1]);

            if (pair < 1 && myCoin > 1) { 
                for (int card : tempCards) {
                    if (tempCards.contains(n + 1 - card)) {
                        pair++;
                        myCoin -= 2;
                        tempCards.remove(card); 
                        break;
                    }
                }
            }

            if (pair < 1) { 
                break;
            }
            round++;
            pair--;
        }

        return round;
    }

    private void matchCard(int card) {
        if (myCoin > 0 && myCards.contains(n + 1 - card)) { 
            myCards.remove(n + 1 - card);
            myCoin--;
            pair++;
            return;
        }
        tempCards.add(card); 
    }
}