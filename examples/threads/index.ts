const threadFunction = (id: number) => {
    console.log(`Thread ${id} started`)
    setTimeout(() => {
        console.log(`Thread ${id} finished`)
    }, 2000)
}

const threads = []
for (let i = 0; i < 5; i++) {
    threads.push(
        new Promise((resolve) => {
            threadFunction(i)
            resolve(() => console.log(`Thread ${i} has finished`))
        }),
    )
}

Promise.all(threads).then(() => {
    console.log('All threads have finished')
})
