## Home page developing document

### Demand analysis

1. Contents to display

   - Frontend Request Analysis: 
     - Display all the articles in particular order page by page.
     - Display top articles at the top.
     - Display the most popular tags.
     - Display archives.
     - Display popular articles.

   - Backend Interfaces: 

     | URL                  | Parameter     | Method | Return Content                           |
     | -------------------- | ------------- | ------ | ---------------------------------------- |
     | ``/article``         | ``pageParm``  | Post   | List of articles in current page.        |
     | ``/article/hot``     | ``Integer n`` | Get    | List of the ``n`` most popular articles. |
     | ``/article/new``     | ``Integer n`` | Get    | List of the ``n`` newest articles.       |
     | ``/article/archive`` |               | Get    | List of archivedo.                       |
     | ``/tag/hot``         | ``Integer n`` | Get    | List of the ``n`` most popular tags.     |

### Backend Implementation

1. Layering:

   I divide the backend apis into **controller layer**,**service layer**, **dao layer** where classes at the controller layer are responsible for responsing the frontent requests, those at the service layer are responsible for transform the data in database in to other forms and those at the dao layer are responsible for querying or modifying data in database.

2. Interfaces implementation:

   - Interface at ``/article``: First sort articles in order of weight(top) and release date,then use ``selectPage`` method in ``MybatisPlus`` to query articles in the page.
   - Interface at ``/article/hot`` ,``/article/new`` and ``/tag/hot``: Uses select command with limit condition to query the list of articles.
   - Interface at ``/article/archive``: Uses select command to query articles and group by their date, then use ``count()`` function to count numbers in each date.

