-- Lua Script: get_cache.lua

-- KEYS[1] = "cset"
-- KEYS[2] = "ccnt"

local exists = redis.call('EXISTS', KEYS[1])

if exists == 1 then
    -- "cset" exists, retrieve all members
    local members = redis.call('SMEMBERS', KEYS[1])
    return {1, members}  -- 1 indicates that cache exists
else
    -- "cset" does not exist, check "ccnt"
    local cnt = redis.call('GET', KEYS[2])
    if not cnt then
        -- "ccnt" does not exist, treat as no cache
        return {0, {}}
    else
        cnt = tonumber(cnt)
        if cnt > 0 then
            -- "ccnt" > 0, cache has expired
            return {2, {}}
        else
            -- "ccnt" == 0, no cache
            return {0, {}}
        end
    end
end