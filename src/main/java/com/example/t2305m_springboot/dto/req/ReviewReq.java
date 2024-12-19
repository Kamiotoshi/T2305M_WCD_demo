package com.example.t2305m_springboot.dto.req;

public class ReviewReq {
    private Long orderId; // ID của đơn hàng cần đánh giá
    private int rating;   // Đánh giá sao (1-5)
    private String comment; // Bình luận

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
