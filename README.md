# TinoniBot

A useful bot for discord using Kord

> **Warning**: This bot is under development

## To run this bot

> Tinoni uses [supabase](https://supabase.com/) to store and read data, so you need to create a [supabase](https://supabase.com/) account.

Arguments to run this bot, in this order:
- Discord bot token
- Supabase project url: 
> You need to put this at the end of the project url `/rest/v1/`
- Supabase api key

If you don't provide these arguments the bot will not start.

## Commands

This bot contains useful commands for moderation, utility or music.

### Chat Commands

| Name | Command | Arguments | Description      |
|------|---------|-----------|------------------|
| Ping | !ping   |           | Get bot latency. |

### Slash Commands

| Name                 | Command                 | Arguments | Description                            |
|----------------------|-------------------------|-----------|----------------------------------------|
| UpdateWelcomeChannel | /update channel welcome | channel   | Updates welcome channel for new users. |
| RemoveWelcomeChannel | /remove channel welcome |           | Remove welcome channel from database.  |

## Events

This bot contains useful events for moderation.

| Name             | Description                                          |
|------------------|------------------------------------------------------|
| MemberJoinEvent  | Sends a welcome message to the configured channel.   |
| GuildCreateEvent | When bot joins a guild, adds guild to database.      |
| GuildDeleteEvent | When bot exist a guild, removes guild from database. |