@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage postsPage

@template.layout.page(
    content = @`
        <h1>Посты</h1>

       <nav aria-label="Page navigation example">
         <ul class="pagination">
         @if (postsPage.getPage() == 0)
              <li class="page-item"><a class="page-link" href="/posts?page=1">Previous</a></li>
         @endif
         @if (postsPage.getPage() > 1)
               <li class="page-item"><a class="page-link" href="/posts?page=${postsPage.getPage() - 1}">Previous</a></li>
         @endif
           <li class="page-item"><a class="page-link" href="/posts?page=${postsPage.getPage()}">${postsPage.getPage()}</a></li>
           <li class="page-item"><a class="page-link" href="/posts?page=${postsPage.getPage() + 1}">Next</a></li>
         </ul>
       </nav>

        <table class="table table-striped">
            @for(var post : postsPage.getPosts())
                <tr>
                    <td>
                       <a href ="${NamedRoutes.postPath(post.getId())}">${post.getId()}</a>
                    </td>
                    <td>
                       ${post.getName()} ${post.getBody()}
                    </td>

                </tr>
            @endfor
        </table>
    `
)
