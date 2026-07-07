package com.example.demoswagger.tool;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

@SuppressWarnings("ALL")
public class ToolElearning {
    static class DataEntry {
        String a;
        String scoid;
        String sesskey;
        String cookie;

        DataEntry(String a, String scoid, String sesskey, String cookie) {
            this.a = a;
            this.scoid = scoid;
            this.sesskey = sesskey;
            this.cookie = cookie;
        }
    }

    // Hàm lấy giá trị tham số từ URL
    private static String getParamValue(String url, String param) {
        String[] parts = url.split("\\?");
        if (parts.length < 2) return null;
        String query = parts[1];
        String[] params = query.split("&");
        for (String p : params) {
            if (p.startsWith(param + "=")) {
                return p.substring((param + "=").length());
            }
        }
        return null;
    }

    private static final Logger logger = Logger.getLogger(ToolElearning.class.getName());

    static {
        try {
            // Tắt handler mặc định
            Logger rootLogger = Logger.getLogger("");
            for (Handler handler : rootLogger.getHandlers()) {
                rootLogger.removeHandler(handler);
            }

            // Tạo custom console handler log ra stdout
            @SuppressWarnings("java:S106")
            Handler consoleHandler = new StreamHandler(System.out, new SimpleFormatter() {
                private static final String FORMAT = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(FORMAT,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            }) {
                @Override
                public synchronized void publish(LogRecord lr) {
                    super.publish(lr);
                    flush(); // Quan trọng: flush sau mỗi log
                }
            };

            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        logger.info("Nhập URL (chứa a, scoid, sesskey): ");
        String urlInput = scanner.nextLine();
        // Parse các tham số a, scoid, sesskey từ URL
        String aStart = getParamValue(urlInput, "a");
        String scoidStart = getParamValue(urlInput, "scoid");
        String sesskey = getParamValue(urlInput, "sesskey");
        logger.info("Nhập cookie (toàn bộ chuỗi Cookie từ trình duyệt hoặc request): ");
        String cookie = scanner.nextLine();
        List<DataEntry> dataList = new ArrayList<>();
        int aStartInt = Integer.parseInt(aStart);
        int scoidStartInt = Integer.parseInt(scoidStart);
        for (int i = 0; i < 100; i++) {
            int aVal = aStartInt + i;
            int scoidVal = scoidStartInt + i * 2;
            dataList.add(new DataEntry(
                    String.valueOf(aVal),
                    String.valueOf(scoidVal),
                    sesskey,
                    cookie
            ));
        }
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (DataEntry entry : dataList) {
            executor.submit(() -> {
                try {
                    logger.info("Thread " + Thread.currentThread().getName() + " - Gửi request cho a=" + entry.a + ", scoid=" + entry.scoid);
                    callDatamodelApi(entry);
                    callPlayerApi(entry);
                    logger.info("Thread " + Thread.currentThread().getName() + " - Hoàn thành cho a=" + entry.a + ", scoid=" + entry.scoid);
                } catch (Exception e) {
                    logger.severe("Lỗi khi gọi API cho a=" + entry.a + ", scoid=" + entry.scoid + ": " + e.getMessage());
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        scanner.close();
    }

    private static void callDatamodelApi(DataEntry entry) throws IOException {
        String urlString = "https://elearning.acb.vn/mod/scorm/datamodel.php";
        HttpURLConnection con = null;

        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            // Set headers
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,vi;q=0.8");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Origin", "https://elearning.acb.vn");
            con.setRequestProperty("Referer", "https://elearning.acb.vn/mod/scorm/player.php?a=" + entry.a + "&currentorg=2.1_5_giai_%C4%91o%E1%BA%A1n_ph%C3%A1t_tri%E1%BB%83n_c%E1%BB%A7a_%C4%90%E1%BB%99i_ORG&scoid=" + entry.scoid + "&sesskey=" + entry.sesskey + "&display=popup&mode=normal");
            con.setRequestProperty("Sec-Fetch-Dest", "empty");
            con.setRequestProperty("Sec-Fetch-Mode", "cors");
            con.setRequestProperty("Sec-Fetch-Site", "same-origin");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36");
            con.setRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"141\", \"Not?A_Brand\";v=\"8\", \"Chromium\";v=\"141\"");
            con.setRequestProperty("sec-ch-ua-mobile", "?0");
            con.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
            con.setRequestProperty("Cookie", entry.cookie);

            String postData = "id=" +
                    "&a=" + URLEncoder.encode(entry.a, "UTF-8") +
                    "&sesskey=" + URLEncoder.encode(entry.sesskey, "UTF-8") +
                    "&attempt=1" +
                    "&scoid=" + URLEncoder.encode(entry.scoid, "UTF-8") +
                    "&cmi__core__lesson_status=passed";

            try (OutputStream os = con.getOutputStream()) {
                os.write(postData.getBytes());
                os.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new IllegalArgumentException("Datamodel API response code: " + responseCode);
            }
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private static void callPlayerApi(DataEntry entry) throws IOException {
        String urlString = String.format(
                "https://elearning.acb.vn/mod/scorm/player.php?sesskey=%s&display=popup&mode=normal&a=%s&scoid=%s",
                URLEncoder.encode(entry.sesskey, "UTF-8"),
                URLEncoder.encode(entry.a, "UTF-8"),
                URLEncoder.encode(entry.scoid, "UTF-8")
        );

        HttpURLConnection con = null;
        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            // Set headers
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,vi;q=0.8");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Referer", "https://elearning.acb.vn/mod/scorm/player.php?a=" + entry.a + "&currentorg=EQU_3.4_-_Ph%C6%B0%C6%A1ng_ph%C3%A1p_nh%E1%BA%ADn_th%E1%BB%A7c_x%C3%A3_h%E1%BB%99i_ORG&scoid=" + entry.scoid + "&sesskey=" + entry.sesskey + "&display=popup&mode=normal");
            con.setRequestProperty("Sec-Fetch-Dest", "empty");
            con.setRequestProperty("Sec-Fetch-Mode", "cors");
            con.setRequestProperty("Sec-Fetch-Site", "same-origin");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36");
            con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"141\", \"Not?A_Brand\";v=\"8\", \"Chromium\";v=\"141\"");
            con.setRequestProperty("sec-ch-ua-mobile", "?0");
            con.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
            con.setRequestProperty("Cookie", entry.cookie);

            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new IllegalArgumentException("Player API response code: " + responseCode);
            }
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
