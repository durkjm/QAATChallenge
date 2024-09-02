import http from 'k6/http';
import { check } from 'k6';
import { BASE_URL } from './config.js';

export function createPet(pet) {
    let response = http.post(`${BASE_URL}/pet`, JSON.stringify(pet), {
        headers: { 'Content-Type': 'application/json' },
    });

    check(response, {
        'Create status 200': (r) => r.status === 200,
    });

    return response;
}

export function getPetById(petId) {
    let response = http.get(`${BASE_URL}/pet/${petId}`);

    check(response, {
        'Get status 200': (r) => r.status === 200,
    });

    return response;
}

export function deletePetById(petId) {
    let response = http.del(`${BASE_URL}/pet/${petId}`);

    check(response, {
        'Delete status 200': (r) => r.status === 200,
    });

    return response;
}
