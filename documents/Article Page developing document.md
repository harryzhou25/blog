## Article Page

1. Create new articles

   - Frontend Request Analysis:

     - Request to create new article with given content, title, summary ,content in html version, category id, author id and list of tags.

   - Vo design

     - articleParam: which contains given variables

   - Backend Interfaces:

     | URL                  | Parameter    | Method | Return data |
     | -------------------- | ------------ | ------ | ----------- |
     | ``/article/publish`` | articleParam | Post   | String      |

   - Details of implementation:

     - First the request will be intercepted to check whether the user was logged in or not.
     - The related information will be inserted the table of article, articleBody and tags.

2. Editing articles

   - Frontend Request Analysis:

     - Request updating article content, title or summary.

   - Backend Interface:

     | URL               | Parameter    | Method | Return data |
     | ----------------- | ------------ | ------ | ----------- |
     | ``/article/edit`` | articleParam | Post   | String      |

   - Details of implementation:

     - First the request will be intercepted to check whether the user was logged in or not.
     - Update article content, content_html, title and summary of the record in table with same article id.
     - For each tag, if it exists in table but not in given list, then delete this tag. If the tag exists in the given list but not in table, insert it into table.