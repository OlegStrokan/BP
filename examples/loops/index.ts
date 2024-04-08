async function sendRequests(): Promise<void> {
    for (let i = 1; i <= 5; i++) {
        const response = await          fetch(`https://api.example.com/data/${i}`);
        console.log(`Response for request ${i}: ${response.status}`);
    }
}

sendRequests();
