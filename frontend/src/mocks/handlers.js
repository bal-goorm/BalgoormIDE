import { rest } from 'msw'

export const handlers = [
    rest.post('/login', (req, res, ctx) => {
        const { id, password } = req.body;
        if(id === 'test' && password === '1234') {
            return res(
                ctx.status(200),
                ctx.json({
                token: 'token',
                role: 'user',
                message: 'login successful'
            }))
        }
        return res(
            ctx.status(401),
            ctx.json({
                message: 'login failed'
            })
        )
    }),
];