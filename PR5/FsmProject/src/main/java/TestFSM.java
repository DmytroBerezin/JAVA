public class TestFSM {
    private final boolean fixBugs;
    private char state;

    /**
     * @param fixBugs Якщо false - використовується оригінальна логіка зі схеми.
     *                Якщо true - використовується виправлена логіка (завдання 3*).
     */
    public TestFSM(boolean fixBugs) {
        this.fixBugs = fixBugs;
        this.state = 'S';
    }

    public String processString(String inputString) {
        this.state = 'S';

        if (inputString == null) {
            return String.valueOf(this.state);
        }

        for (char ch : inputString.toCharArray()) {
            if (this.state == 'F') {

                continue;
            }

            if (!this.fixBugs) {
                originalTransitions(ch);
            } else {
                fixedTransitions(ch);
            }
        }

        return String.valueOf(this.state);
    }

    private void originalTransitions(char ch) {
        switch (this.state) {
            case 'S':
                this.state = (ch == 'T') ? '1' : 'S';
                break;
            case '1':
                this.state = (ch == 'E') ? '2' : 'S';
                break;
            case '2':
                this.state = (ch == 'S') ? '3' : 'S';
                break;
            case '3':
                this.state = (ch == 'T') ? 'F' : 'S';
                break;
        }
    }

    private void fixedTransitions(char ch) {
        switch (this.state) {
            case 'S':
                this.state = (ch == 'T') ? '1' : 'S';
                break;
            case '1':
                if (ch == 'E') {
                    this.state = '2';
                } else if (ch == 'T') {
                    this.state = '1';
                } else {
                    this.state = 'S';
                }
                break;
            case '2':
                if (ch == 'S') {
                    this.state = '3';
                } else if (ch == 'T') {
                    this.state = '1';
                } else {
                    this.state = 'S';
                }
                break;
            case '3':
                this.state = (ch == 'T') ? 'F' : 'S';
                break;
        }
    }
}