import java.util.*;

class Finite1 {
    public String processInput(int st, int ts, int[][] s, int initial, int fin, String[] in) {
        StringBuilder result = new StringBuilder();
        result.append("Transition table:\n");
        result.append("              0   1\n");
        for (int i = 0; i < st; i++) {
            result.append("state ").append(i).append(" ");
            for (int j = 0; j < ts; j++) {
                result.append("s").append(s[i][j]).append(" ");
            }
            result.append("\n");
        }
        int current = initial;
        int ip, nextstate;
        for (int i = 0; i < in.length; i++) {
            result.append("s").append(current).append("--").append(in[i]).append("-->");
            ip = Integer.parseInt(in[i]);
            nextstate = s[current][ip];
            current = nextstate;
            if (i == (in.length - 1)) {
                result.append("s").append(current); //.append("\n");
            }
        }
        if (current == fin) {
            result.append("\nAccepted");
        } else {
            result.append("\nNot Accepted");
        }
        return result.toString();
    }
}
