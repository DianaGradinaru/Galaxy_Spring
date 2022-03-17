const api = {
    get: async (url) => {
        const req = await fetch(url, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
        });
        if (req.ok) {
            const res = await req.json();
            return res;
        }
        return "GET error";
    },
    delete: async (url) => {
        const req = await fetch(url, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
        });
        if (req.ok) {
            const res = await req.json();
            return res;
        }
        return "DELETE error";
    },
    post: async (url, data) => {
        const req = await fetch(url, {
            mode: "cors",
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });
        if (req.ok) {
            const res = await req.json();
            return res;
        }
        return "POST error";
    },
    put: async (url, data) => {
        const req = await fetch(url, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });
        if (req.ok) {
            const res = await req.json();
            return res;
        }
        return "PUT error";
    },
};

export default api;
