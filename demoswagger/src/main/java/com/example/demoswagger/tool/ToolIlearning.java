package com.example.demoswagger.tool;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
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
public class ToolIlearning {

    static class DataEntry {
        String a;
        String scoid;
        String sesskey;
        String cookie;
        String attempt;
        String score;
        String suspendData;

        DataEntry(String a, String scoid, String sesskey, String cookie, String attempt, String score, String suspendData) {
            this.a = a;
            this.scoid = scoid;
            this.sesskey = sesskey;
            this.cookie = cookie;
            this.attempt = attempt;
            this.score = score;
            this.suspendData = suspendData;
        }
    }

    @SuppressWarnings({"java:S4830", "java:S1135", "java:S5527"})
    private static void disableSSLVerification() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            /* TODO document why this method is empty */
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            // TODO document why this method is empty
                        }
                    }
            };

            String protocol = "SSL";
            SSLContext sc = SSLContext.getInstance(protocol);
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
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
                return p.substring(param.length() + 1);
            }
        }
        return null;
    }

    private static final Logger logger = Logger.getLogger(ToolIlearning.class.getName());

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
        // Disable SSL verification first
        disableSSLVerification();

        Scanner scanner = new Scanner(System.in);

        logger.info("Nhập URL (chứa a, scoid, sesskey): ");
        String urlInput = scanner.nextLine();

        // Parse các tham số từ URL
        String aStart = getParamValue(urlInput, "a");
        if (aStart == null) {
            aStart = getParamValue(urlInput, "cm");
        }
        String scoidStart = getParamValue(urlInput, "scoid");
        String sesskey = getParamValue(urlInput, "sesskey");

        logger.info("Nhập cookie (MoodleSession): ");
        String cookie = scanner.nextLine();

        logger.info("Nhập attempt (mặc định 2): ");
        String attemptInput = scanner.nextLine();
        String attempt = attemptInput.isEmpty() ? "2" : attemptInput;

        logger.info("Nhập score (mặc định 7.14): ");
        String scoreInput = scanner.nextLine();
        String score = scoreInput.isEmpty() ? "7.14" : scoreInput;

        logger.info("Nhập suspend_data (để trống nếu không có): ");
        String suspendData = scanner.nextLine();

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
                    cookie,
                    attempt,
                    score,
                    suspendData
            ));
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (DataEntry entry : dataList) {
            executor.submit(() -> {
                try {
                    logger.info(Thread.currentThread().getName() + " - Gửi request cho a=" + entry.a + ", scoid=" + entry.scoid);
                    callDatamodelApi(entry);
                    callPlayerApi(entry);
                    logger.info("✅ Success cho a=" + entry.a + ", scoid=" + entry.scoid);
                } catch (Exception e) {
                    logger.severe("❌ Lỗi khi gọi API cho a=" + entry.a + ", scoid=" + entry.scoid + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        scanner.close();
    }

    private static void callDatamodelApi(DataEntry entry) throws IOException {
        String urlString = "https://ilearning.acb.com.vn/mod/scorm/datamodel.php";

        HttpURLConnection con = (HttpURLConnection) new URL(urlString).openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        // Set headers
        setCommonHeaders(con, entry);
        con.setRequestProperty("priority", "u=0, i");
        con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Origin", "https://ilearning.acb.com.vn");
        con.setRequestProperty("Referer", buildRefererUrl(entry));

        // Build và gửi POST data
        String postData = buildPostData(entry);
        try (OutputStream os = con.getOutputStream()) {
            os.write(postData.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        validateResponse(con, entry.a, "Datamodel");
    }

    private static void callPlayerApi(DataEntry entry) throws IOException {
        String urlString = String.format(
                "https://ilearning.acb.com.vn/mod/scorm/player.php?sesskey=%s&display=popup&mode=normal&a=%s&scoid=%s",
                URLEncoder.encode(entry.sesskey, "UTF-8"),
                URLEncoder.encode(entry.a, "UTF-8"),
                URLEncoder.encode(entry.scoid, "UTF-8")
        );

        HttpURLConnection con = (HttpURLConnection) new URL(urlString).openConnection();
        con.setRequestMethod("GET");

        // Set headers
        setCommonHeaders(con, entry);
        con.setRequestProperty("Referer", buildRefererUrl(entry));
        con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        con.setRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"141\", \"Not?A_Brand\";v=\"8\", \"Chromium\";v=\"141\"");
        con.setRequestProperty("sec-ch-ua-mobile", "?0");
        con.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");

        validateResponse(con, entry.a, "Player");
    }

    // Helper methods
    private static void setCommonHeaders(HttpURLConnection con, DataEntry entry) {
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,vi;q=0.8");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Sec-Fetch-Dest", "empty");
        con.setRequestProperty("Sec-Fetch-Mode", "cors");
        con.setRequestProperty("Sec-Fetch-Site", "same-origin");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36");
        con.setRequestProperty("Cookie", entry.cookie);
    }

    private static String buildRefererUrl(DataEntry entry) {
        return String.format(
                "https://ilearning.acb.com.vn/mod/scorm/player.php?a=%s&curr…rticulate_rise&scoid=%s&sesskey=%s&display=popup&mode=normal",
                entry.a, entry.scoid, entry.sesskey
        );
    }

    private static String buildPostData(DataEntry entry) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("id=")
                .append("&a=").append(URLEncoder.encode(entry.a, "UTF-8"))
                .append("&sesskey=").append(URLEncoder.encode(entry.sesskey, "UTF-8"))
                .append("&attempt=").append(URLEncoder.encode(entry.attempt, "UTF-8"))
                .append("&scoid=").append(URLEncoder.encode(entry.scoid, "UTF-8"))
                .append("&cmi__core__score__raw=").append(URLEncoder.encode(entry.score, "UTF-8"));

        if (entry.suspendData != null && !entry.suspendData.isEmpty()) {
            builder.append("&cmi__suspend_data=").append(URLEncoder.encode(entry.suspendData, "UTF-8"));
        }

        builder.append("&cmi__core__lesson_status=passed");
        return builder.toString();
    }

    private static void validateResponse(HttpURLConnection con, String aValue, String apiName) throws IOException {
        int responseCode = con.getResponseCode();
        logger.info(String.format("%s API Response Code: %d for a=%s", apiName, responseCode, aValue));

        if (responseCode != 200) {
            throw new IllegalArgumentException(String.format("%s API response code: %d", apiName, responseCode));
        }
    }
}
