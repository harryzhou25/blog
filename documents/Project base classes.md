## Project base classes and data tables

### Data tables

**article**

| Column name | Data type | Description                                                  |
| ----------- | --------- | ------------------------------------------------------------ |
| id          | bigint    | Primary-key and auto increment.                              |
| create_date | bigint    | Used to record the create date of each article and display in article page. |
| summary     | varchar   | Used to display in home page                                 |
| title       | varchar   | Used to display in home page                                 |
| view_counts | int       | Used to record the number of view of each article, which would be helpful when sorting according to popularity and will be displayed in the home page and article page. |
| author_id   | bigint    | Foreign key, which will be used to bind article and author.  |
| body_id     | bigint    | Foreign key, which will be used to bind article and article content. |
| category_id | int       | Foreign key, which will be used to bind article and article category. |

**article body**

| Column name | Data type | Description                                                  |
| ----------- | --------- | ------------------------------------------------------------ |
| id          | bigint    | Primary-key and auto increment.                              |
| content     | longtext  | Article content                                              |
| article_id  | bigint    | Foreign key, which will be used to bind article body and article. |

**article tag**

| Column name | Datatype | Description                                                  |
| ----------- | -------- | ------------------------------------------------------------ |
| id          | bigint   | Primary-key and auto increment.                              |
| article_id  | bigint   | Foreign key, which will be used to bind article and article tag. |
| tag_id      | bigint   | Foreign key, which will be used to bind article and article tag. |

**tag**

| Column name | Datatype | Description                     |
| ----------- | -------- | ------------------------------- |
| id          | bigint   | Primary-key and auto increment. |
| tag_name    | varchar  | Name of the tag                 |
| avatar      | varchar  | The display photo of the tag    |

**system user**

| Column name | Datatype | Description                          |
| ----------- | -------- | ------------------------------------ |
| id          | bigint   | Primary-key and auto increment.      |
| account     | varchar  | User account                         |
| admin       | bit      | To mark whether the user is an admin |
| avatar      | varchar  | The avatar of user                   |
| create_date | bigint   | Register date of user                |
| email       | varchar  | User email                           |
| last_login  | bigint   | The time of last login               |
| mobile      | varchar  | User mobile number                   |
| nickname    | varchar  | User name                            |
| password    | varchar  | User password                        |
| status      | varchar  | User status                          |

### Entity classes

**pojo**

- Sys_user
- Article
- Tag

**vo**

- pageParam (related to paging)
- tagvo
- articlevo