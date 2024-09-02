import { sleep } from 'k6';
import { createPet, getPetById, deletePetById } from '../apiUtils.js';

export let options = {
    vus: 10,
    duration: '30s',
};

export default function () {
    const pet = {
        id: 10,
        name: 'Rambo',
        category: { id: 1, name: 'Dogs' },
        photoUrls: ['http://example.com/photo'],
        tags: [{ id: 0, name: 'Tag1' }],
        status: 'available',
    };

    createPet(pet);
    getPetById(10);
    deletePetById(10);

    sleep(1);
}
