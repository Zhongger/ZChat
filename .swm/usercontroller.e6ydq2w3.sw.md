---
title: usercontroller更新说明
---
<SwmSnippet path="/src/main/java/com/zhongger/zchat/controller/UserController.java" line="37">

---

&nbsp;

```java
        addUserToList(userList, "ZMY", "123456");
        addUserToList(userList, "LL", "12345678");
    
        int batchInsertUser = userService.batchInsertUser(userList);
        return "success insert " + batchInsertUser;
    }
    
    private void addUserToList(List<User> userList, String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userList.add(user);
    }
```

---

</SwmSnippet>

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBWkNoYXQlM0ElM0FaaG9uZ2dlcg==" repo-name="ZChat"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
