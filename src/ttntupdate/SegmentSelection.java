package ttntupdate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


    

class Segment implements Comparable<Segment> {
    int start, end;

    Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // Tính độ dài của đoạn thẳng
    int length() {
        return this.end - this.start;
    }

    // So sánh hai đoạn thẳng dựa trên độ dài của chúng
    @Override
    public int compareTo(Segment other) {
        return this.length() - other.length();
    }

    // Kiểm tra xem hai đoạn thẳng có giao nhau không
    boolean intersects(Segment other) {
        return this.start < other.end && other.start < this.end;
    }
}

public class SegmentSelection {


    public static void main(String[] args) {
        Segment[] segments = {
            new Segment(1, 4),
            new Segment(3, 5),
            new Segment(2, 6),
            new Segment(7, 9),
            new Segment(8, 10)
    };

        List<Segment> selectedSegments = selectSegments(segments);
        System.out.println("Selected segments:");
        for (Segment seg : selectedSegments) {
            System.out.println("[" + seg.start + ", " + seg.end + "]");
        }
    }

    public static List<Segment> selectSegments(Segment[] segments) {
        // Bước 1: Sắp xếp các đoạn thẳng
        Arrays.sort(segments);

        // Bước 2: Lựa chọn các đoạn thẳng
        List<Segment> S = new ArrayList<>();
        for (Segment current : segments) {
            boolean intersects = false;
            for (Segment selected : S) {
                if (current.intersects(selected)) {
                    intersects = true;
                    break;
                }
            }
            if (!intersects) {
                S.add(current);
            }
        }
        return S;
    }
}




