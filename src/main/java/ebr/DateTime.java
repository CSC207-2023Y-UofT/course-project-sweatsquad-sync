public class DateTime {
    private int time;
    private int[] dateArr;

    public void setDate(int day, int month, int year) {
        this.dateArr = new int[]{day, month, year};
    }

    public int[] getDate() {
        return this.dateArr;
    }

    public void setTime(int numIntervals) {
        // Precondition: 0 <= numIntervals < 48
        this.time = numIntervals;
    }

    public int getTime() {
        return this.time;
    }

    public int compareDateTime(DateTime other) {
        //returns -1 if other before this, 1 if other after this, 0 if they are equal

        if (other.dateArr[2] < this.dateArr[2]) {
            return -1;
        } else if (other.dateArr[2] > this.dateArr[2]) {
            return 1;
        }

        if (other.dateArr[1] < this.dateArr[1]) {
            return -1;
        } else if (other.dateArr[1] > this.dateArr[1]) {
            return 1;
        }

        if (other.dateArr[0] < this.dateArr[0]) {
            return -1;
        } else if (other.dateArr[0] > this.dateArr[0]) {
            return 1;
        }

        if (other.time < this.time) {
            return -1;
        } else if (other.time > this.time) {
            return 1;
        }

        return 0;
    }

    public int getTimeDifference(DateTime other) {
        // Precondition: other and this are on the same day
        return Math.abs(other.time - this.time);
    }

    @Override
    public String toString() {
        String date = this.dateArr[2] + "/" + this.dateArr[1] + "/" + this.dateArr[0];
        String hour = Integer.toString(this.time / 2);
        String mins;
        if (this.time % 2 == 1) {
            mins = "30";
        } else {
            mins = "00";
        }
        return hour + ":" + mins + " on " + date;
    }

    public void addTime(int numIntervals) {
        // Precondition: Adding this time does not change the current date
        this.time += numIntervals;
    }

    public void subtractTime(int numIntervals) {
        // Precondition: Subtracting this time does not change the current date
        this.time -= numIntervals;
    }
}
