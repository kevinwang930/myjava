-- Lua Script: create_cache.lua

-- KEYS[1] = "cset"
-- KEYS[2] = "ccnt"
-- ARGV = list of integers to be cached

-- Add list members to "cset" in one request
redis.call('SADD', KEYS[1], unpack(ARGV))

-- Set "ccnt" to the number of list members
local count = #ARGV
redis.call('SET', KEYS[2], count)

-- Return success status
return 1
