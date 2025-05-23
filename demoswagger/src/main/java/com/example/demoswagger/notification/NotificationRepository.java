package com.example.demoswagger.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    String mQueryPushPromotion = "SELECT a.notification_token " +
            "FROM tbl_User a " +
            "INNER JOIN tbl_Account b " +
            "ON a.user_id=b.account_user_id " +
            "AND b.account_balance>=200 " +
            "GROUP BY a.notification_token " +
            "ORDER BY a.user_id ";

    // Account have balance >= 200
    @Query(value = mQueryPushPromotion, nativeQuery = true)
    List<String> pushPromotionNotification();

    String mQueryPushAll = "SELECT a.user_id as notification_user_id, a.full_name, " +
            "a.notification_token, b.account_number, b.account_balance " +
            "FROM tbl_User a " +
            "INNER JOIN tbl_Account b " +
            "ON a.user_id=b.account_user_id " +
            "ORDER BY a.user_id ";

    @Query(value = mQueryPushAll, nativeQuery = true)
    List<Notification> pushAllNotification();
}
