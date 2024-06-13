function confirmDelete() {
    return confirm("Are you sure you want to delete your account?");
}


async function uploadFile() {
    let formData = new FormData();
    formData.append("file", ajaxfile.files[0]);
    await fetch('controller', {
        method: "POST",
        body: formData
    });
    alert('The file upload with Ajax and Java was a success!');
}
