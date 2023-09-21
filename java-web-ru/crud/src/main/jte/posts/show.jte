@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
    content = @`
        <div>
            <p>${page.post.getName()} ${page.post.getBody()}</p>

        </div>

    `
)

