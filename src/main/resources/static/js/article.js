const deleteButton = document.getElementById('delete-btn');
if (deleteButton) {
    deleteButton.addEventListener('click', (e) => {
        if (!confirm('삭제 하시겠습니까?')) return;

        let id = document.getElementById('article-id').value;

        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/articles');
        });
    });
}

const modifyButton = document.getElementById('modify-btn');
if (modifyButton) {
    modifyButton.addEventListener('click', (e) => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() =>{
           alert('수정이 완료되었습니다.');
           location.replace(`/article/${id}`);
        });
     });
}

const createButton = document.getElementById('create-btn');
if (createButton) {
    createButton.addEventListener("click", (e) => {
        fetch("/api/articles", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('등록이 완료되었습니다.');
            location.replace("/articles");
        });
    });
}