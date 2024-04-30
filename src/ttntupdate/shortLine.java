package ttntupdate;
import java.util.Arrays;
import java.util.Comparator;

public class shortLine {

    static class Segment {
        int start;
        int end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        // Khởi tạo các đoạn thẳng
        Segment[] segments = {
            new Segment(1, 4),
            new Segment(3, 5),
            new Segment(2, 6),
            new Segment(7, 9),
            new Segment(8, 10)
        };

        // Gọi hàm để tìm tập hợp lớn nhất các đoạn thẳng không giao nhau
        Segment[] nonOverlapping = findNonOverlappingSegments(segments);

        // In kết quả
        System.out.println("Các đoạn thẳng không giao nhau:");
        for (Segment s : nonOverlapping) {
            System.out.println("(" + s.start + ", " + s.end + ")");
        }
    }

    public static Segment[] findNonOverlappingSegments(Segment[] segments) {
        // Sắp xếp các đoạn thẳng theo điểm bắt đầu
        Arrays.sort(segments, Comparator.comparingInt(s -> s.start));

        // Tìm tập hợp lớn nhất các đoạn thẳng không giao nhau
        int n = segments.length;
        boolean[] selected = new boolean[n];
        selected[0] = true;
        int lastSelectedIndex = 0;

        for (int i = 1; i < n; i++) {
            if (segments[i].start > segments[lastSelectedIndex].end) {
                selected[i] = true;
                lastSelectedIndex = i;
            }
        }

        // Trả về các đoạn thẳng đã chọn
        return Arrays.stream(segments)
                     .filter(s -> selected[Arrays.asList(segments).indexOf(s)])
                     .toArray(Segment[]::new);
    }
}
