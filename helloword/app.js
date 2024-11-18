const http = require('http');
const { exec } = require('child_process');

const hostname = '127.0.0.1';
const port = 8000;

// Function to check if a port is already in use
function isPortInUse(port, callback) {
    exec(`lsof -i:${port}`, (error, stdout) => {
        if (error) {
            // Error occurred or no process is using the port
            callback(false);
        } else {
            // Check if any output exists, indicating port in use
            callback(stdout.trim().length > 0);
        }
    });
}
// Function to start the server
function startServer() {
    const server = http.createServer((req, res) => {
        res.statusCode = 200;
        res.setHeader('Content-Type', 'text/plain');
        res.end('Hello World');
    });

    server.listen(port, hostname, () => {
        console.log(`Server running at http://${hostname}:${port}/`);
    });
}

function killProcessUsingPort(port, callback) {
    exec(`kill $(lsof -ti:${port})`, (error, stdout, stderr) => {
        if (error) {
            console.error(`Error killing process on port ${port}: ${error}`);
            callback(error);
        } else {
            console.log(`Process on port ${port} killed`);
            callback(null);
        }
    });
}

isPortInUse(port, (inUse) => {
    if (inUse) {
        // Port is in use, kill the process and start the server
        killProcessUsingPort(port, (error) => {
            if (!error) {
                startServer();
            }
        });
    } else {
        // Port is not in use, start the server
        startServer();
    }
});