package View;

import AI.CourseClass;
import AI.Schedule;
import Model.InputFromMySQL;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ViewExcel {

    Schedule schedule;
    private static final int DAY_HOURS = ViewSchedule.getDAY_HOURS();
    private static final int ROOM_NUM = ViewSchedule.getROOM_NUM();
    private final String fileName = "Schedule.xls";

    // data to write file
    private Object[][] data = {{"ROOM : ", "MON", "TUE", "WED", "THU", "FRI"},
    {"06h45 - 07h30", null, null, null, null, null},
    {"07h35 - 08h20", null, null, null, null, null},
    {"08h25 - 09h10", null, null, null, null, null},
    {"09h15 - 10h00", null, null, null, null, null},
    {"10h05 - 10h50", null, null, null, null, null},
    {"10h55 - 11h30", null, null, null, null, null},
    {"12h30 - 13h15", null, null, null, null, null},
    {"13h20 - 14h05", null, null, null, null, null},
    {"14h10 - 14h55", null, null, null, null, null},
    {"15h00 - 15h45", null, null, null, null, null},
    {"15h50 - 16h35", null, null, null, null, null},
    {"16h40 - 17h25", null, null, null, null, null}};

    public ViewExcel(Schedule schedule) {
        this.schedule = schedule;
    }

    // create and write new file *.xls
    public void writeFileExcel() {
        WritableWorkbook workbook;
        // create workbook
        try {
            workbook = Workbook.createWorkbook(new File(fileName));

            // create sheet
            WritableSheet sheet1 = workbook.createSheet("AI-Genetic Algorithm", 0);

            // create Label and add to sheet
            sheet1.addCell(new Label(0, 0, "Making a Class Schedule Using a Genetic Algorithm "));

            // row begin write data
            int rowBegin = 2;
            int colBegin = 0;

            for (int x = 0; x < ROOM_NUM; x++) {
                loadTableByRoom(x);
                for (int row = rowBegin, i = 0; row < data.length + rowBegin; row++, i++) {
                    for (int col = colBegin, j = 0; col < data[0].length + colBegin; col++, j++) {
                        sheet1.addCell(new Label(col, row, (String) data[i][j]));
                    }
                }
                colBegin += data[0].length + 1;
            }
            // write file
            workbook.write();

            // close
            workbook.close();
            System.out.println("create and write success");
            JOptionPane.showMessageDialog(null, "Export Success!");
        } catch (IOException e) {
            System.out.println("Error create file\n" + e.toString());
            JOptionPane.showMessageDialog(null, "Error create file!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (RowsExceededException e) {
            System.out.println("Error write file\n" + e.toString());
            JOptionPane.showMessageDialog(null, "Error write file!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (WriteException e) {
            System.out.println("Error write file\n" + e.toString());
            JOptionPane.showMessageDialog(null, "Error write file!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Hien thi thong tin cua tiet hoc
    private String showLesson(int i) {
        StringBuilder s = new StringBuilder();
        if (schedule.getSlots().get(i).size() > 0) {
            for (CourseClass cc : schedule.getSlots().get(i)) {
                s.append(cc.getId()).append("\n");
                s.append(cc.getCourse().getName()).append("\n");
                s.append(cc.getProfessor().getName()).append("\n");
                if (cc.isLabRequired()) {
                    s.append("lab\n");
                }
                s.append(cc.getNumberOfSeats());
            }
        }
        return s.toString();
    }

    @SuppressWarnings("empty-statement")
    //data tro den lich cua phong roomList[i]
    private void loadTableByRoom(int i) {
        data = new Object[][]{{"ROOM : " + InputFromMySQL.getRoomList().get(i).getName() + "\n" + (InputFromMySQL.getRoomList().get(i).isLab() ? "lab\n" : "\n") + InputFromMySQL.getRoomList().get(i).getNumberOfSeats(), "MON", "TUE", "WED", "THU", "FRI"},
        {"06h45 - 07h30", showLesson(i * DAY_HOURS + 0), showLesson(i * DAY_HOURS + 0 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 4 * DAY_HOURS * ROOM_NUM)},
        {"07h35 - 08h20", showLesson(i * DAY_HOURS + 1), showLesson(i * DAY_HOURS + 1 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 4 * DAY_HOURS * ROOM_NUM)},
        {"08h25 - 09h10", showLesson(i * DAY_HOURS + 2), showLesson(i * DAY_HOURS + 2 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 4 * DAY_HOURS * ROOM_NUM)},
        {"09h15 - 10h00", showLesson(i * DAY_HOURS + 3), showLesson(i * DAY_HOURS + 3 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 4 * DAY_HOURS * ROOM_NUM)},
        {"10h05 - 10h50", showLesson(i * DAY_HOURS + 4), showLesson(i * DAY_HOURS + 4 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 4 * DAY_HOURS * ROOM_NUM)},
        {"10h55 - 11h30", showLesson(i * DAY_HOURS + 5), showLesson(i * DAY_HOURS + 5 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 4 * DAY_HOURS * ROOM_NUM)},
        {"12h30 - 13h15", showLesson(i * DAY_HOURS + 6), showLesson(i * DAY_HOURS + 6 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 4 * DAY_HOURS * ROOM_NUM)},
        {"13h20 - 14h05", showLesson(i * DAY_HOURS + 7), showLesson(i * DAY_HOURS + 7 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 4 * DAY_HOURS * ROOM_NUM)},
        {"14h10 - 14h55", showLesson(i * DAY_HOURS + 8), showLesson(i * DAY_HOURS + 8 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 4 * DAY_HOURS * ROOM_NUM)},
        {"15h00 - 15h45", showLesson(i * DAY_HOURS + 9), showLesson(i * DAY_HOURS + 9 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 4 * DAY_HOURS * ROOM_NUM)},
        {"15h50 - 16h35", showLesson(i * DAY_HOURS +10), showLesson(i * DAY_HOURS +10 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +10 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +10 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +10 + 4 * DAY_HOURS * ROOM_NUM)},
        {"16h40 - 17h25", showLesson(i * DAY_HOURS +11), showLesson(i * DAY_HOURS +11 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +11 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +11 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +11 + 4 * DAY_HOURS * ROOM_NUM)}
        };
    }
}
