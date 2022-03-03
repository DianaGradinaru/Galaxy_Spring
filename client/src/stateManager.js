import { atom } from "jotai";

const state = {
    cardsAtom: atom([
        {
            id: null,
            created_at: null,
            image: null,
            message: null,
            sent_by_id: null,
            username: null,
        },
    ]),
};

export default state;
