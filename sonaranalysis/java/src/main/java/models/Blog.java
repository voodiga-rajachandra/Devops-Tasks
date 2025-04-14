package models;

public class Blog {
    private int id, userId;
    private String name, description, content, creatorName;

    public Blog() {}

    public Blog(int id, int userId, String name, String description, String content, String creatorName) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.content = content;
        this.creatorName = creatorName;
    }

    // Getters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getContent() { return content; }
    public String getCreatorName() { return creatorName; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setContent(String content) { this.content = content; }
    public void setCreatorName(String creatorName) { this.creatorName = creatorName; }
}
