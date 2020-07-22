-- // CB-7832 all deleted on provider side set to available
-- Migration SQL that makes the change goes here.

--- update the status of instance to CREATED where stack is deleted on provider side
UPDATE instancemetadata
set instancestatus = 'CREATED'
where instancegroup_id in (
    select ig.id
    from instancegroup ig
             join stack s on ig.stack_id = s.id
             join stackstatus ss on ss.id = s.stackstatus_id
    where ss.status = 'DELETED_ON_PROVIDER_SIDE'
);

--- update to AVAILABLE the deleted on provider side
UPDATE stackstatus ss
SET status = 'AVAILABLE',
    detailedstackstatus = 'AVAILABLE',
    statusreason = ''
FROM stack s
WHERE ss.stack_id = s.id
  and ss.status = 'DELETED_ON_PROVIDER_SIDE';


-- //@UNDO
-- SQL to undo the change goes here.


