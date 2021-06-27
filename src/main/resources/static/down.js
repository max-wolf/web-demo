handleExport = () => {
    const req = new XMLHttpRequest();
    req.open('POST', "http://localhost:8080/download/streamDownload", true);
    req.responseType = 'blob';
    req.setRequestHeader('Content-Type', 'application/json');
    req.onload = () => {
        const data = req.response;
        const a = document.createElement('a');
        const blob = new Blob([data],{type:"application/octet-stream"});
        const blobUrl = window.URL.createObjectURL(blob);
        this.download(blobUrl) ;
    };
    req.send('');
};

download = (blobUrl) => {
    const a = document.createElement('a');
    a.style.display = 'none';
    a.download = '11.jpg'; //文件名
    a.href = blobUrl;
    a.click();
    // document.body.removeChild(a);
}
