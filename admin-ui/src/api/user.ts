import request from "@/api/request";

export function login(username: string, password: string) {
    return request.post("/login", {username: username, password: password});
}