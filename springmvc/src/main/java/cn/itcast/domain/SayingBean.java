package cn.itcast.domain;

import java.util.Date;

/**
 * @author brink
 * 2020/9/29 15:01
 * CREATE TABLE saying(id INT AUTO_INCREMENT, content VARCHAR(100), type VARCHAR(10),bg VARCHAR(100),createTime DATETIME);
 *
 * INSERT INTO saying VALUES (null, '这是一个安静的晚上', 'rainbow', '', null);
 */
public class SayingBean {
    private String id;
    private String content;
    private String type;
    private String bg;
    private Date createTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
