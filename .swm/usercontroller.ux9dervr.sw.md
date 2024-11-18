---
title: UserController
---
# Introduction

This document will walk you through the implementation of the <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="19:4:4" line-data="public class UserController {">`UserController`</SwmToken> feature.

The feature introduces a new controller for handling user-related operations in the application.

We will cover:

1. Why the <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="19:4:4" line-data="public class UserController {">`UserController`</SwmToken> was introduced.
2. How the <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="19:4:4" line-data="public class UserController {">`UserController`</SwmToken> handles adding a single user.
3. How the <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="19:4:4" line-data="public class UserController {">`UserController`</SwmToken> handles adding multiple users in a batch.

# <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="19:4:4" line-data="public class UserController {">`UserController`</SwmToken> introduction

<SwmSnippet path="/src/main/java/com/zhongger/zchat/controller/UserController.java" line="10">

---

The <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="19:4:4" line-data="public class UserController {">`UserController`</SwmToken> is introduced to manage user-related operations. It is annotated with <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="18:0:1" line-data="@RestController">`@RestController`</SwmToken> to indicate that it is a Spring MVC controller where every method returns a domain object instead of a view.

```

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongmingyi
 * @date 2021/10/11 11:50 上午
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
```

---

</SwmSnippet>

# Adding a single user

<SwmSnippet path="/src/main/java/com/zhongger/zchat/controller/UserController.java" line="22" collapsed>

---

The <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="28:6:6" line-data="    @PostMapping(&quot;/addUser&quot;)">`addUser`</SwmToken> method handles HTTP POST requests to add a single user. It uses the <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="28:1:2" line-data="    @PostMapping(&quot;/addUser&quot;)">`@PostMapping`</SwmToken> annotation to map the request to the <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="28:5:6" line-data="    @PostMapping(&quot;/addUser&quot;)">`/addUser`</SwmToken> endpoint. The method takes a <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="29:10:10" line-data="    public String addUser(@RequestBody User user) {">`User`</SwmToken> object from the request body and calls the <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="30:3:3" line-data="        int insertUser = userService.insertUser(user);">`insertUser`</SwmToken> method of <SwmToken path="/src/main/java/com/zhongger/zchat/controller/UserController.java" pos="21:3:3" line-data="    private UserService userService;">`UserService`</SwmToken> to insert the user into the database.

```

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        int insertUser = userService.insertUser(user);
        return "success insert " + insertUser;
    }
```

---

</SwmSnippet>

# Adding multiple users in a batch

<SwmSnippet path="/src/main/java/com/zhongger/zchat/controller/UserController.java" line="34">

---

The method efficiently handles HTTP GET requests to add multiple users in a batch. It intelligently uses the annotation to accurately map the request to the designated endpoint. The method dynamically creates a list of objects, seamlessly adds users to the list, and expertly calls the designated method of the specified class to effortlessly insert the users into the database.

```
    @GetMapping("/batchAddUserTest")
    public String batchAddUserTest() {
        List<User> userList = new ArrayList<>();
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
}

```

---

</SwmSnippet>

This approach allows for efficient handling of multiple user insertions in a single request, reducing the overhead of multiple database transactions.

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBWkNoYXQlM0ElM0FaaG9uZ2dlcg==" repo-name="ZChat"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
